from crewai.tools import BaseTool
from typing import Type
from pydantic import BaseModel, Field


class DBInsertToolInput(BaseModel):
    """Input schema for DBInsertTool."""
    argument: str = Field(..., description="Description of the argument.")

# Following says that DBInsertTool inherited from BaseTool.
class DBInsertTool(BaseTool):
    name: str = "This tool stores the user or customer feedback or comment in a database"
    description: str = (
        "This tools is useful for storing the user or customer feedback or comment in a database"
    )
    args_schema: Type[BaseModel] = DBInsertToolInput

    def _run(self, argument: str) -> str:
        # Implementation goes here
        return "Your feedback has been stored in the database " + argument
