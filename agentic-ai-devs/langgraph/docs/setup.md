## Steps to configure environment for your notebook

### Project structure

Open langgraph folder in your VSCode to get it working properly  

```
my_project/
├── .venv/                  # Isolated virtual environment (never commit to git)
├── notebooks/              # Jupyter Notebooks for experimentation & EDA
│   └── 01_exploration.ipynb
├── src/                    # Source code directory for production scripts
│   ├── __init__.py
│   └── utils.py
├── data/                   # (Optional) Store local data variants
│   ├── raw/
│   └── processed/
├── .gitignore              # Ignores .venv, cache files, and checkpoint files
├── README.md               # Project documentation
└── requirements.txt        # Tracked dependencies (e.g., ipykernel, pandas)

```

### Environment setup

- Open terminal
- python -m venv .venv (under langgrph folder)
- Point VS Code to the manual environment: Open the Command Palette (ctrl + shift + p) and select Python: Select Interpreter.- Choose the Python path located inside your new .venv folder.

### How to configure keys for your LLMs

Following assumes that you already have keys generated in your AI platforms

- Create a new file .env
- Add lines similar to following to this file
    - PENAI_API_KEY="key-here"
    - GOOGLE_API_KEY="key-here"

### How to install packages

Terminal > New Terminal  

- pip install -U langchain_openai langchain_core langgraph
- pip install --upgrade langchain langgraph
- pip install langchain-google-genai

abc