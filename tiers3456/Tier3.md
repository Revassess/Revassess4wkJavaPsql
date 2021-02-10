# Tier 3

**Category:** SQL and AWS RDS

**Difficulty:** Intermediate I

**Estimated time to complete:** 45-60 minutes

**Minimum completion:** 60 points

**Description/Setup:**
  - ERD: [Quizzard Entity Relationship Diagram](https://revature-note-assets.s3.amazonaws.com/quizzard-erd.png)
  - Dummy data script:
    - Oracle: [Quizzard Dummy Data Script](https://revature-note-assets.s3.amazonaws.com/quizzard-dummy-data.sql)
    - PostgreSQL: [Quizzard Dummy Data Script](https://example-for-java-curriculum.s3.amazonaws.com/dummy_data.sql)


**Problem Set:**
  - Create an AWS RDS Instance. If you already have one, then the existing instance will suffice. Place the url and credentials for accessing the database inside the com.revature.config.ConnectionUtil class using the provided fields. 
    - Value: 10 points

  - Create a sequence that produces numbers beginning at 6 and increments by 3 each time. Place the name of the created sequence inside the com.revature.config.ConnectionUtil class's TIER_3_SEQUENCE_NAME field.
    - Value: 20 Points

 - Create a table structure using the provided ERD, ensure that the ability for auto-incrementing IDs and fill the tables with the dummy data provided in the sql script in the Setup section of this README document.
    - Value: 30 points
