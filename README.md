# Catering Java Application

A Java-based educational application simulating the analysis, design, and development of a business-oriented system, following a simplified **Unified Process (UP)** methodology.

## Quick Setup Guide

Follow the steps below to configure and run the project locally.

### 1. Requirements

- Java SDK **19**
- A Java-compatible IDE (e.g. IntelliJ, Eclipse, VS Code)

### 2. Recommended Tools

- [Docker](https://docs.docker.com/engine/install/)
- [Visual Studio Code](https://code.visualstudio.com/) with extension: ["Extension Pack for Java"](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)

To start coding, open the `java/` directory in your IDE.  
Example in VS Code: `File` → `Open Folder` → select `java`.

## 3. Database Setup

The application requires a **MySQL** database. If Docker is installed, you can spin up `MySQL` and `Adminer` with:

```bash
cd database
docker compose up -d
```

This launches two containers:

- `MySQL`: the actual database service
- `Adminer`: a web-based UI (available at `http://localhost:8080`)

After startup:

- Visit Adminer on `http://localhost:8080`
- Log in using:
  - System: `MySQL`
  - Server: `mysql`
  - Username: `root`
  - Password: `root` (or as configured)

- Create a new database named `catering`
- Run the SQL script `catering_init_03.sql` to create initial tables

Note: port and credentials can be changed in `docker-compose.yml` if needed.

### Database Access Configuration

```bash
java/catering/persistence/PersistenceManager.java
```

Update the following fields as needed:

- `host`
- `port`
- `username`
- `password`

Note: default configuration is aligned with the Docker setup (host: `localhost`, port: `3306`, user: `root`, password: `root`).

## Overview

This lab project simulates a real-world software development lifecycle by implementing:

- Requirements analysis
- System design
- Application development

It follows an educational adaptation of the Unified Process (UP) methodology, aimed at fostering understanding of:

- Business logic modeling
- Persistence layer integration
- Modular application design
