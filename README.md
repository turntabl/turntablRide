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