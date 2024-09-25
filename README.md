# Social Media Clone Application

## Overview

This is a social media clone application built using Java and Spring Boot. The application provides features such as user authentication, posting, commenting, liking, following, and chatting.
* NOTE: The project is on the MASTER branch, not main

## Features

* **User Authentication**: Users can register and log in to the application.
* **Posting**: Users can create and share posts with text, images, and other media.
* **Commenting**: Users can comment on posts.
* **Liking**: Users can like posts and comments.
* **Following**: Users can follow other users to see their posts.
* **Chatting**: Users can send and receive messages with other users.

## Technical Details

* **Backend**: The application is built using Java and Spring Boot.
* **Database**: The application uses a relational database management system (RDBMS) to store data.
* **Repository Layer**: The application uses Spring Data JPA to interact with the database.
* **Service Layer**: The application uses Spring Boot's service layer to provide business logic.
* **Mapper Layer**: The application uses mapper classes to transform data between layers.

## Database Schema

The application uses the following database schema:

* **CommentReply**: Stores comment replies with attributes such as `id`, `text`, `numOfLikes`, `idComment`, and `idUser`.
* **LikedReply**: Stores liked replies with attributes such as `id`, `idUser`, and `idCommentReply`.
* **Post**: Stores posts with attributes such as `id`, `description`, `dateCreated`, `image`, and relationships with `User`, `Comment`, and `Like`.
* **Comment**: Stores comments with attributes such as `id`, `text`, `numOfLikes`, and relationships with `User` and `Post`.
* **Like**: Stores likes with attributes such as `id`, `dateLiked`, and relationships with `User` and `Post`.
* **User**: Stores users with attributes such as `id`, `email`, `username`, and `resetToken`.
* **Message**: Stores messages with attributes such as `id`, `text`, `dateSent`, and relationships with `User` and `Conversation`.

## API Endpoints

Next are listed some of the more interesting API endpoints.

* **PUT /users/{id}**: Updates a user's profile information.
* **POST /posts**: Creates a new post.
* **GET /users/{id}/posts**: Retrieves a list of posts made by a specific user.
* **GET /users/{id}/comments**: Retrieves a list of comments made by a specific user.
* **POST /comments**: Creates a new comment.
* **PUT /comments/{id}**: Updates a comment's content.
* **POST /likes**: Creates a new like.
* **POST /users/{id}/follow**: Follows a user.
* **DELETE /users/{id}/follow**: Unfollows a user.
* **GET /users/{id}/followers**: Retrieves a list of users who follow a specific user.
* **GET /users/{id}/following**: Retrieves a list of users who are followed by a specific user.

### Chat Endpoints

Chatting is made possible through using WebSockets

* **POST /conversations**: Creates a new conversation.
* **GET /conversations**: Retrieves a list of conversations.
* **POST /messages**: Sends a new message.
* **GET /messages**: Retrieves a list of messages.

## Running the Application

To run the application, follow these steps:

1. Clone the repository: `git clone https://github.com/your-username/social-media-clone.git`
2. Navigate to the project directory: `cd social-media-clone`
3. Build the project: `mvn clean package`
4. Run the application: `java -jar target/social-media-clone.jar`

## Contributing

Contributions are welcome! If you'd like to contribute to the project, please fork the repository and submit a pull request.
