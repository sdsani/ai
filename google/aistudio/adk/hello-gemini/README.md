# My First Agent using Google ADK

First Agent using ADK

## Environment setup

- Create a folder for your project code (myfirstagent)
- [Install gcloud sdk](https://docs.cloud.google.com/sdk/docs/install-sdk)
    - Authenticate: gcloud auth login
- Install google ADK (framework, cli, dependencies for working with Google's Demini models)
    - Install ADK (Agent Development Kit)
        - Change folder to myfirstagent
        - Install packages: sudo apt install python3-pip python3-venv -y
        - Create pip virtual environment: python3 -m venv .venv
        - Activate: source .venv/bin/activate
        - Install ADK: python -m pip install google-adk
        - Verify: adk --version
- Get your API key 
    - Google AI studio (Good option for learning)
    - Gemini Enterprise Agent Platform (For production) 
		https://aistudio.google.com/api-keys
- Enable Agent API in the project thru console
- [Follow the instruction for java](https://adk.dev/get-started/java/)

## System Requirements
- JDK 17 or later
- Python 3.11.X or higher
- Init your Java project with gradle: gradle init --type java-application

## Hello World
- [Java Quick Start for ADK](https://adk.dev/get-started/java/)
- Setup environment variables for your API key:
```
  export GOOGLE_API_KEY="YOUR_API_KEY"
  export GOOGLE_PROJECT
  
  Or environment variable in your IDE run profile.
```

## Test Result

Switching to SpringBoot project was straight forward.

```
You > Hello are you there?

Agent > Hello! Yes, I am here. How can I help you today? If you'd like to know the current time in any city, just let me know!

You > quit

Process finished with exit code 0

```