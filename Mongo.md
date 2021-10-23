##### Get rid of all your Mongo PPAs.

```cmd
~ ❯ sudo ls /etc/apt/sources.list.d
mongodb-org-4.4.list  yarn.list
~ ❯ sudo rm -i /etc/apt/sources.list.d/mongodb-org-4.4.list
```

##### Remove all Mongo packages and bits and pieces.

```cmd
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

##### Add new PPA and install Mongo 4.4.

```cmd
wget -qO - https://www.mongodb.org/static/pgp/server-4.4.asc | sudo apt-key add -
echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu focal/mongodb-org/4.4 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-4.4.list
sudo apt-get update
sudo apt-get upgrade
sudo apt-get install -y mongodb-org
```

##### Or Install Mongo new version

```cmd
sudo apt install mongodb
```

##### Start

```cmd
sudo systemctl start mongod
```
