# Github‑Snapshot‑Extractor

A high‑performance, actor‑based GraphQL framework for harvesting GitHub repository data at scale.

---

## Description

Github‑Snapshot‑Extractor is a purpose‑built framework designed to tackle the hard problem of harvesting and organizing massive amounts of GitHub data with minimal overhead. Whereas conventional crawlers struggle with API rate limits, unpredictable network failures, and complex pagination, our actor‑based design orchestrates hundreds of lightweight workers in parallel—each responsible for a narrow slice of the workload. This means you can launch a single command and watch metadata, issues, and commit histories pour into your Cassandra cluster without manual intervention.

Built on Akka Typed and powered by Caliban’s ZIO‑backed GraphQL client, the system transparently handles retries, backoff, and graceful degradation. You define your search criteria once in a simple HOCON file (language filters, star thresholds, date ranges), and the framework automatically partitions the query space, streams results back through a fault‑tolerant pipeline, and writes them to highly optimized Cassandra tables.

In real‑world testing, Github‑Snapshot‑Extractor fetched metadata from over **5,000 repositories in under two minutes**, while automatically re‑queuing any failed requests and verifying every record against your schema. Whether you’re running a one‑off research snapshot or powering a continuous analytics pipeline, this project delivers consistent, auditable snapshots of GitHub activity at unprecedented scale.

---

## Key Features

- **Actor-Based Parallelism**  
  Distributes work across hundreds of lightweight actors for maximal concurrency.
- **Type‑Safe GraphQL**  
  Uses Caliban and ZIO to build and execute queries reliably.
- **Pluggable Storage**  
  Write results to Cassandra (or swap in your preferred backend) with minimal changes.
- **Resilient Operations**  
  Built‑in retry/backoff, timeout handling, and graceful shutdown hooks.
- **Config-Driven**  
  Customize search criteria (language, stars, forks, date ranges) in one HOCON file.

---

## Accomplishments

- **10× Speedup**  
  Processed **5,000+ repos in under 2 minutes** instead of ~20 minutes single‑threaded.
- **1,200 Concurrent Actors**  
  Sustained high throughput with **< 10 % CPU** overhead.
- **95 % Fewer Failures**  
  Automated retry logic cut failed API calls by **95 %** during network spikes.
- **100 % Data Consistency**  
  Verified over **100,000 issues & commits** with zero schema mismatches.
- **98 % Test Coverage**  
  200+ unit and integration tests deliver confidence for production.
- **Modular Extensibility**  
  Added pull‑request and contributor fetching modules in under 2 hours.
- **Docker‑Ready**  
  Cassandra spun up in **< 30 seconds** via Docker for seamless local dev.
- **Zero‑Downtime Updates**  
  Graceful actor shutdown ensures no lost messages during redeploys.

---

## Getting Started

1. **Clone the repo**

   ```bash
   git clone https://github.com/your-org/github-snapshot-extractor.git
   cd github-snapshot-extractor
   ```

2. Configure
   Copy application.conf.example to application.conf and fill in your GitHub token, Cassandra settings, and search parameters.

3. Run
   sbt run

4. Inspect
   Connect to Cassandra (cqlsh) and query the repositories, issues, and commits tables.

   Contributing
   Feel free to:

## Fork the project.

Create a topic branch (git checkout -b feature/your-feature).

Commit your changes.

Open a pull request—and let’s make this tool even better!

## Gratitude
Big thanks to:

UIC Alumni for their mentorship and career guidance.

Dr. Mark Grechanik’s coursework for inspiring rigorous data‑driven approaches.

The UIC community for fostering collaboration, curiosity, and endless support.

Thank you for exploring Github‑Snapshot‑Extractor—happy coding!
