/*
 rule.summary=Response ETag header cannot be empty
 rule.description=Validates the 'ETag' header in the response
*/



assert response.header('ETag').isNotEmpty(): "Could not find 'ETag' header in response"