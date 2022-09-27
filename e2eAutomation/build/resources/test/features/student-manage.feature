Feature: Manage (CRUD) students data
  Logged in user should be able to manage (create, read update and delete) data about students in the course
  -------------------
  As a logged user
  I want to manage data about students in the course
  So that I can keep the track of the students enrolled
  -------------------

  Scenario: Successful creation of new student (record)
    Given And I am logged in user
      And I'm on the "http://wagner.wang/ContosoUniversity/Students" page
      When I click on "Create New" link
      And I enter last name in "Last Name" field
      And I enter first name in "First Name" field
      And I enter date in "Enrollment Date" field
      And I click on "Create" button
    Then new student record will be created and I'll be redirected to student page
      And I can see that student is created by finding student with search

  Scenario: Successful reading of existing student's data
  # todo

  Scenario: Successful editing of existing student
  # todo

  Scenario: Successful (record) deletion of existing student
  # todo