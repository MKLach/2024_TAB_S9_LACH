# Use the official Microsoft SQL Server image as a base
FROM mcr.microsoft.com/mssql/server

# Set environment variables
ENV ACCEPT_EULA=Y
ENV SA_PASSWORD=HjklQwerty9876

# Create a directory for the SQL scripts
RUN mkdir -p /tmp/sql

# Copy SQL scripts to the container
COPY ./sql /tmp/sql

# Run the SQL scripts to create the custom database
CMD /bin/bash -c '/opt/mssql/bin/sqlservr & sleep 30 & /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P HjklQwerty9876 -i /tmp/sql/create-database.sql & /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P HjklQwerty9876 -i /tmp/sql/init-database.sql & wait'