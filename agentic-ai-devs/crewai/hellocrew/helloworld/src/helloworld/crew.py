from crewai import Agent, Crew, Process, Task
from crewai.project import CrewBase, agent, crew, task
from crewai.agents.agent_builder.base_agent import BaseAgent


# Helloworld is inherited from CrewBase
@CrewBase
class Helloworld():
    """Helloworld crew"""

    agents: list[BaseAgent]
    tasks: list[Task]

    # https://docs.crewai.com/en/learn/using-annotations
    @agent
    def researcher(self) -> Agent:

        print("Creating researcher agent...")
        return Agent(
            config=self.agents_config['researcher'], # type: ignore[index]
            verbose=True
        )

    @agent
    def reporting_analyst(self) -> Agent:
        print("Creating reporting analyst agent...")
        return Agent(
            config=self.agents_config['reporting_analyst'], # type: ignore[index]
            verbose=True
        )

    @task
    def research_task(self) -> Task:
        print("Creating research task...")
        return Task(
            config=self.tasks_config['research_task'], # type: ignore[index]
        )

    @task
    def reporting_task(self) -> Task:
        print("Creating reporting task...")
        return Task(
            config=self.tasks_config['reporting_task'], # type: ignore[index]
            output_file='report.md'
        )

    @crew
    def crew(self) -> Crew:
        """Creates the Helloworld crew"""
        print("Creating Helloworld crew...")
        return Crew(
            agents=self.agents,
            tasks=self.tasks,
            process=Process.sequential,
            verbose=True,
        )
