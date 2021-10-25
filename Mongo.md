##### Install

```cmd
sudo apt install mongodb
```

##### Enable

```
sudo systemctl enable mongod
```

##### Restart

```cmd
sudo systemctl restart mongod
```

##### status

```
sudo systemctl status mongod
```

##### Start

```cmd
sudo systemctl start mongod
```


#### Fix bug

```
System has not been booted with systemd as init system (PID 1). Can't operate.
Failed to connect to bus: Host is down
```

Get rid of all your Mongo PPAs.

```
~ ❯ sudo ls /etc/apt/sources.list.d
mongodb-org-4.4.list  yarn.list
~ ❯ sudo rm -i /etc/apt/sources.list.d/mongodb-org-4.4.list
Remove all Mongo packages and bits and pieces.
```

```
sudo rm -r /var/log/mongodb
sudo rm -r /var/lib/mongodb
sudo dpkg --remove --force-remove-reinstreq mongo-tools
sudo dpkg --remove --force-remove-reinstreq mongodb-org
sudo dpkg --remove --force-remove-reinstreq mongodb-org-server
sudo dpkg --remove --force-remove-reinstreq mongodb-server-core
sudo dpkg --remove --force-remove-reinstreq mongodb-org-mongos
sudo dpkg --remove --force-remove-reinstreq mongodb-org-shell
sudo dpkg --remove --force-remove-reinstreq mongodb-org-tools
sudo apt-get --fix-broken install
yes | sudo apt autoremove
```

Add new PPA and install Mongo 4.4.

```
wget -qO - https://www.mongodb.org/static/pgp/server-4.4.asc | sudo apt-key add -
echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu focal/mongodb-org/4.4 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-4.4.list
sudo apt-get update
sudo apt-get upgrade
sudo apt-get install -y mongodb-org
```
You'll get the same error from before. This is caused by mongodb-org package trying to use systemctl to start the DB after install ([source](https://www.reddit.com/r/bashonubuntuonwindows/comments/i2r7oa/cant_install_mongodb_on_wsl_1_ubuntu_2004/g06ntkr/?utm_source=share&utm_medium=web2x&context=3)). Notice that the same error is thrown when running the command manually.

```
~ ❯ sudo systemctl start mongod
System has not been booted with systemd as init system (PID 1). Can't operate.
Failed to connect to bus: Host is down
```

However, running mongod should still work!

If you run into errors when running mongod, you might need to modify some permissions and create a data directory. I found the answer to these issues [here](https://stackoverflow.com/questions/29813648/failed-to-unlink-socket-file-error-in-mongodb-3-0/34290982#34290982), [here](https://stackoverflow.com/questions/37702957/mongodb-data-db-not-found/43033484#43033484), and [here](https://stackoverflow.com/questions/42446931/mongodb-exception-in-initandlisten-20-attempted-to-create-a-lock-file-on-a-rea/42447303#42447303).

```
sudo chown $USER /tmp/mongodb-27017.sock
sudo mkdir -p /data/db
sudo chown -R $USER /data/db
```

The problem is that the directory you created, /data/db is owned by and only writable by the root user, but you are running mongod as yourself. There are several ways to resolve this, but ultimately, you must give the directory in question the right permissions. If this is for production, I would advise you to check the docs and think this over carefully -- you probably want to take special care.

However, if this is just for testing and you just need this to work and get on with it, you could try this, which will make the directory writable by everyone:

```
> sudo chmod -R go+w /data/db
```

or this, which will make the directory owned by you:

```
> sudo chown -R $USER /data/db
```

You need to create this directory as root

Either you need to use sudo , e.g. ```sudo mkdir -p /data/db```

Or you need to do su - to become superuser, and then create the directory with ```mkdir -p /data/db```

or you can simply set the permissions of the .sock file to the current user:

```
sudo chown `whoami` /tmp/mongodb-27017.sock
```