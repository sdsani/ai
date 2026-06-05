## Steps to configure environment for your notebook

### Environment setup

- Open your project folder in VS Code via File > Open Folder....
- Press Ctrl + Shift + P (Windows/Linux) or Cmd + Shift + P (Mac) to open the Command Palette.
- Type and select Python: Create Environment.
- Choose your environment type: Select Venv for a standard virtual environment or Conda if you use Anaconda.
- Select the Python interpreter version you want to use from the list.

### How to configure keys for your LLMs

Following assumes that you already have keys generated in your AI platforms

- Create a new file .env
- Add lines similar to following to this file
    - PENAI_API_KEY="key-here"
    - GOOGLE_API_KEY="key-here"
