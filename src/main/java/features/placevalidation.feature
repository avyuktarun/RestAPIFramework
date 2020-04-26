Feature: Validating Place API's 

@AddPlace
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI

	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "Post" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "GetPlaceAPI"

Examples:
	|name |language |address  |
	|MVG  |English  |Sai Chowk|
#	|SVD  |French   |Sarvdharm|
#	|LLP  |German   |KKNagar  |

@DeletePlace
Scenario: Verify if delete place functionalty is working

	Given DeletePlace Payload
	When user calls "DeletePlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"