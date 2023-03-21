# turntablRide
Ride booking application for turntablr's

Google API Creds
Auth Url: https://accounts.google.com/o/oauth2/auth

Access Token Url: https://oauth2.googleapis.com/token

Grant type: Authorization Code

## Authentication

All APIs routes are protected. The API expects a Bearer Token that can be obtained by performing
an oauth2 log in on the client using legitimate credentials and requesting an access token from
the oauth2 provider, Google in this case.
The Oauth2 log in request will require a client id ad client secret

## Instructions for OAuth2 setup on Spring for Development

Retrieve client-id and client-secret from Google API Console for turntablRides project.

The active profile has been indicated as dev

Create `application-dev.yml`

Add the following entries

CLIENT_ID: [insert client-id here]

CLIENT_SECRET: [insert client-secret here]

Note that the same client-id and secret is what front end clients would use to obtain an access token that the Spring Backend can use for authentication

## Database Connection

Using MongoDB Atlas for storing the application data.
To connect to the db, inside the `application-dev.yml` file add the entries:

MONGO_DB_DB_NAME: [name of the database given]
MONGO_DB_URI: [link to access the mongodb atlas]

Login into https://cloud.mongodb.com/, to activate the atlas database.