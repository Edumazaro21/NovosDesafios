# models.py
from sqlalchemy import Column, Integer, String
from .database import Base

class Region(Base):
    __tablename__ = "region"

    id = Column(Integer, primary_key=True, index=True)
    region = Column(String, index=True)
    key_word = Column(Integer, index=True)
    country = Column(String, index=True)
    population = Column(Integer)
