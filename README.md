# Title
A simple rest api to store musics and playlists, with session authentication

# Notes about Api
Since The front end is not implemented, and the app uses session authentication,
a post to /playlists does not uses the current logged user as the owner, but a user 
with id of 1 (the first user to register in the plataform).

# Api
```
/authors GET, POST { name: String }
/authors/{id} GET, DELETE 

/categories 
    - GET
    - POST { name: String, parent_id?: long (references categories_id) }

/categories/{id} 
    - DELETE

/playlists 
    - GET

/playlists/{id} 
    - GET
    - DELETE
    - POST { name: String, backgroundImage?: file (nullable) }
    - PUT { name: String, backgroundImage?: file (nullable), id: long }

/playlists/{id}/musics 
    - POST { musicID: long (references musics_id) }
    - DELETE 

/musics
    - GET { search?: string (search title and author), authorID?: long, categoryID?: long, page: long (1-x), pageSize?: int (max = 100), tags?: String[] } 
    - POST { name: String (max = 150), authorID: long, categoryID: long, file: file (The audio file), tags?: String[] }

/musics/{id}
    - GET
    - DELETE

/register 
    - POST { username: String (min = 3 , max = 100), password: String (min = 12) }
    - DELETE ( authenticated )

/login 
    - POST { username: String, password: String }

/logout
    - POST ( authenticated )
```

# Requeriments
- Docker and Docker Compose
- Java version 21 (or use the release bundle)

# Releases
Check the https://github.com/Mateuzz/music-website-spring/releases/ for the last releases.

# Running
- If building from source, run
```
git clone https://github.com/Mateuzz/music-website-spring/ music-website-spring
cd music-website-spring
docker compose up`
mvn spring-boot:run
```
If using the release, simply extract the zip file, enter the directory and run:
```
docker compose up
./java/bin/java -jar java/app.jar
```

The program should be running at localhost:8080.

## Notes
Make sure to have nothing running on port 5432 for the database, if you want to change that
edit the `compose.yaml` file and remap the configuration key `{spring.datasource.url}` in 
`src/main/resources/application.yaml` to `jdbc:postgresql://0.0.0.0:{NEW_PORT}/music`.

# LICENSE 
MIT LICENSE.
