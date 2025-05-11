# distance-calc-challenge
## Setup
| Setup | Version | Resource                                                                                                  |
|-------|---------|-----------------------------------------------------------------------------------------------------------|
| Java  | 17      | install java 17 from [here](https://download.oracle.com/java/17/archive/jdk-17.0.12_windows-x64_bin.msi). |
| Maven | 3.8.6   | download zip file from email.                                                                                            

1. Install Java 17. 
2. Extract apache-maven-3.8.6 into C:/ drive. 
3. Please download postcodes csv files from [here](https://data.freemaptools.com/download/full-uk-postcodes/ukpostcodes.zip). After that, extract and place the csv file in the at project root directory.
## Start Application


## API Header

| Key           | Value | 
|---------------|-------|
| Content-Type  |application/json|
| Authorization |J0KhKfOjsVnSjmXHQc8pgPbZ0++5zM5pr5vi2mPw7Gga8w0m40EfunwsFICtOI39|

## API Endpoints

| Method | Endpoint               | Description                                   | Example                                                               | Body |
|--------|------------------------|-----------------------------------------------|-----------------------------------------------------------------------|---------|
| GET    | /api/distance/postcode | Calculate the distance between two postcodes. | http://localhost:8080/api/distance/postcode?from=AB10 1XG&to=AB10 6RN ||
| POST   | /api/postcode/update   | Update postcode destination.| http://localhost:8080/api/postcode/update|{"postcode":"IM4 2GZ","latitude":54.552294,"longitude":-5.969607}|