/*
 rule.summary=Test for dynamic Base64 decoding
 rule.description= test if base64 decoding dynamic is possible
*/



// Assert body is present
targetMessage.assertBodyNotEmpty()


def dataElement = request.getXPathValue("VANSEnvelope/Message/Data")


assert 4 == 5