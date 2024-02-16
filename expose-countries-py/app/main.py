from fastapi import FastAPI, Depends, HTTPException, Query
from sqlalchemy.orm import Session
from sqlalchemy import func
from . import models, schemas, database
from typing import List, Optional

app = FastAPI()

def get_db():
    db = database.SessionLocal()
    try:
        yield db
    finally:
        db.close()

@app.get("/regions/search")
def read_regions(region: Optional[str] = None,
                 key_word: Optional[int] = None,
                 page: int = Query(1, alias="page"),
                 per_page: int = Query(3, alias="per_page"),
                 db: Session = Depends(get_db)):

    skip = (page - 1) * per_page
    total = db.query(func.count(models.Region.id)).filter(models.Region.region == region, models.Region.key_word == key_word).scalar()
    regions = db.query(models.Region).filter(models.Region.region == region, models.Region.key_word == key_word).offset(skip).limit(per_page).all()

    response = {
        "page": page,
        "per_page": per_page,
        "total": total,
        "total_pages": (total + per_page - 1) // per_page,
        "data": [{
            "name": region.country,
            "region": region.region,
            "population": region.population
        } for region in regions]
    }

    return response