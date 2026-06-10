## Setting up Antigravity in Ubuntu 26.04

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
