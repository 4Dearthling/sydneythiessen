**Postgresql**
- geospatial support
- handles user profiles, appts, messaging threads, progress tracking naturally as they are relational qualities
- 300-500ms latency
- ACID compliance for *preventing double bookings*
- full-text search for matching students and tutors

Cons
- scalability concerns, requires more plannin that firebase's auto-scaling
- less mature SDK so less plugins and extensions

{possibly} **Redis** 
- manages real-time messaging and notifications
- session management for active users
- caching frequently access tutor/student data


**VERSUS**

**Firebase**
- low latency of 100-200ms
- mature SDK
- easy scalability

Cons
- no complex joins 

**GEOSPATIAL SUPPORT: PostGIS** 
- updates every 10 meters of movement
- heartbeat system to track active users
- automatic cleanup
- privacy controls with availability toggles
- 
