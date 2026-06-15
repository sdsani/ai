## Setting up Antigravity in Ubunutu 26.04

Download: 
```
https://antigravity.google/docs/getting-started  
```

**Extract**
```
cd ~/Downloads
tar -xvzf Antigravity.tar.gz
sudo mv Antigravity-x64 /opt/antigravity
```

**Install Sandbox dependencies**
```
sudo chown -R root:root /opt/antigravity
sudo apt install -y libgtk-3-0 libnss3 libatk1.0-0 libatk-bridge2.0-0 libcups2 libdrm2 libxkbcommon0 libgbm1
```

**Create a Desktop Launcher**
```
cat << 'EOF' | sudo tee ~/.local/share/applications/antigravity-ide.desktop
[Desktop Entry]
Name=Antigravity 2.0
Comment=Antigravity 2.0 IDE and Agent
GenericName=IDE
Exec=/opt/antigravity/antigravity %F
Icon=/opt/antigravity/resources/app.asar.unpacked/assets/icon.png
Type=Application
Terminal=false
StartupNotify=true
StartupWMClass=Antigravity
Categories=Development;IDE;
EOF
```

**Update your application database**
```
update-desktop-database ~/.local/share/applications
```

**Navigate to Antigravity directory**
```
cd /opt/antigravity/Antigravity-x64
./antigravity
```

**You may have to run following commands**
```
sudo chown root /opt/antigravity/chrome-sandbox
sudo chmod 4755 /opt/antigravity/chrome-sandbox
```


## Setting up Antigravity in Ubuntu 26.04 (Don't use instructions in this section)

Setup steps: https://antigravity.google/download/linux   

```
sudo mkdir -p /etc/apt/keyrings
curl -fsSL https://us-central1-apt.pkg.dev/doc/repo-signing-key.gpg | \
  sudo gpg --dearmor --yes -o /etc/apt/keyrings/antigravity-repo-key.gpg

echo "deb [signed-by=/etc/apt/keyrings/antigravity-repo-key.gpg] https://us-central1-apt.pkg.dev/projects/antigravity-auto-updater-dev/ antigravity-debian main" | \
  sudo tee /etc/apt/sources.list.d/antigravity.list > /dev/null

sudo apt update

sudo apt install antigravity

sudo apt update && sudo apt upgrade antigravity

run antigravity by typing antigravity in terminal

```

## Switching between editors

```
CTRL + E
CTRL + L
```
