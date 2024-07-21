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

# Building 
1 - Initialize and run the Database:
```
docker compose up`
```
2 - Build the project or run it directly:
```
mvn clean package
cd target;
java -jar music-website-1.0.0-SNAPSHOT.jar;
```
or 
```
mvn spring-boot:run
```

# LICENSE 
MIT LICENSE
