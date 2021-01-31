Feature: Place Api validation

@AddPlace
Scenario Outline: Verfying Add place added using app place API
  Given Add place payload "<name>" "<language>" "<address>"
  When user call "AddPlaceApi" with "Post" http request 
  Then Api "status" in responce body is "OK"
  And Verify place id has same as "<name>" using "GetPlaceApi"
  
Examples:
|name |language |address |
|xyz  |English  |Vancrover BC |
# |Abc  |English  |Toranto ON   |

@DeletePlace
Scenario: Verifying Added Place is deleted
 Given Delete place payload
 When user call "DeletePlaceApi" with "Post" http request
Then Api "status" in responce body is "OK"