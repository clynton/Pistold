Feature: F01 Home page Validation
@smoketest
Scenario: F01 S01 Load home page
Given F01 S01 web browser is open
When F01 S01 user enters home page url
Then F01 S01 browser loads the home page url
And F01 S01 home page title is validated
@regressiontest
Scenario: F01 S02 Validate home page
Given F01 S02 browser is up and home page is loaded
When F01 S02 user clicks home page link
Then F01 S02 browser reloads the url
And F01 S02 page title is revalidated
And F01 S02 the browser is closed

@smoketest @regressiontest
Scenario: F01 S03 Do Something else on home page
Given F01 S03 want to do during smoke and regression
When F01 S03 page x is loaded
Then F01 S03 page x title is validated
