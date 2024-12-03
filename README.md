[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

# PROJECT Design Documentation

## Team Information
* Team name: THE CARiTOGRAPHERS
* Team members
  * JoJo Kaler
  * Shahmir Khan
  * Christian Ashley
  * Tyler Jaafari
  * Andrew Bradbury

## Executive Summary

The maps for RIT students to learn how to get around campus are not sufficient, especially when it comes to indoor connections. We aim to fix this problem by creating a better version of RIT maps that includes route generation through tunnels and connected buildings. We also plan to implement extended features to help students find amenities and other places they might want to go on campus. For example, let's say it's freezing cold outside and you are looking for a place to study on the academic side, but don't want to go outside in the cold to get there. On our map, you will be able to search for an open study space and then select a route that gets you there through the tunnels rather than having to brave the outdoors. 


## Requirements

This section describes the features of the application.

### Definition of MVP
The app will act as an improvement for the existing RIT campus
navigation tools. Users will be able to access a campus map to
look up building locations, such as classes/meetings/dorms, and 
navigate to them using the familiar Google Maps API on their
smart phone. Anyone will be able to access the app, such as
vistors on a campus tour, but students will be able to access 
special featuers. Signing in with an RIT ID will allow students
to add favorite locations and view dorm tunnels.

### MVP Features
- As a user, I want to view the map so I can navigate
    - A user is able to download the app on their smartphone to view a map of RIT campus.
    - A user is able to search locations on campus and receive directions to that location.
- As a user, I want to log in with my university ID to access my account info and permissions.
    - A user can select a menu option that will prompt them to log in with their RIT credentials.
    - A user that provides invalid credentials will be denied access and prompted to re-enter their credentials.
    - A user that provides valid credentials will be logged in as a user that matches the given credentials.
    - A user that is logged in can access a page that shows details related to their account.
- As an administrator, I want to add/edit routes so that the map remains up to date
    - An administrator is able to see a list of student-suggested routes.
    - An administrator is able to approve the route and add it to the routing API
    - An administrator is able to reject a suggested route and remove it from the suggestions list.
    - An administrator is able to edit or remove existing routes.
- As a student, I want to view tunnel routes so that I can navigate the tunnels
    - A student is able to have access to view tunnel routes on campus
    - A student is able to make a route using tunnels
    - A student is able to choose whether or not they would like to have tunnels in their route
- As a student, I want to favorite locations so that I can easily find my frequented locations
    - Favorite locations should be visible only to the specific student
    - Favorite locations should show up when planning routes for easy selection
- As a faculty, I want to add availability information
    - A faculty member is able to view their own availability information
    - A faculty member is able to delete or edit their availability
- As a student, I want to see hours of operation so that I know if I have access to areas on campus
    - A building will have attributes that students can view, like name, hours of operation, college, etc.
    - A student is able to select a building on the map and view its information.
    - A student is able to search for a building and view its information.
- As a student, I want to see accessible facilities such as bathrooms, study rooms, etc. so that I can find places to go
    - A student can select a building, which will display that building’s Details panel.
    - A student viewing the Details panel is shown details about the building and its amenities/facilities.
- As a student, I want to add stops to a route so that I can efficiently reach all of my destinations
    - A student is able to submit travel time to make the data more accurate
    - A student is able to edit a route and add stops, whether indoors or outdoors
    - A student is able to edit the order of my stops on a route
- As a student, I want to give updates on locations and routes so that others can know about obstacles and closures
    - Students are able to submit a comment on a route
    - Students should be able to select a specific location or area to leave their comments, outside of the route algorithm
    - Students should be able to look at their previously commented comments


## Architecture and Design

This section describes the application architecture.

### Software Architecture
![Architecture Diagram for Better Map](/assets/better_maps-architecture_diagram.png)

### Use Cases
![Use case diagram for Better Map](/assets/better_maps.png)

### Domain Model
![Domain model for Better Map](/assets/better_maps-domain_model.png)

#### Sequence Diagram
![Sequence diagram for student login](/assets//better_maps-sequence_diagram.png)

### Class Diagram
![Class diagram for better map](/assets/better_maps-class_diagram.png)

### Code Coverage Results:
![image](https://github.com/user-attachments/assets/affe6541-8e67-4480-b716-8d842d0e8e81)

### Demos

https://github.com/user-attachments/assets/5ce570f6-780a-4648-b1d3-1b167f7c8454

https://github.com/user-attachments/assets/0b70cf5e-6d3e-4d5e-859c-e64092f2617f

https://github.com/user-attachments/assets/ae700570-d9f1-444b-9ba0-a9c6cab3e051
