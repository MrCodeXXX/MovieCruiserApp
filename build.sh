cd MovieCruiserAuthenticationService
source ./env-variable.sh
mvn clean package -DskipTests
docker build -t user-app .
cd ..
cd MovieCruiser
source ./env-variable.sh
mvn clean package -DskipTests
docker build -t movie-app .
cd ..