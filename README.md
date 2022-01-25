# Tweety
A Twitter-API library JAVA

This api contain code for Twitter-Oauth V1, and make request to V2 endpoint using this. 
Example-
```
 
        String consumerKey     = <CONSUMER_KEY>;
        String consumerSecret  = <CONSUMER_SECRET>;
        String token           = <TOKEN>;
        String tokenSecret     = <TOKEN_SECRET>;
        
        tweety = new Tweety(consumerKey, token, tokenSecret, consumerSecret);
        TweetRequest tweetRequest = new TweetRequest();
        tweetRequest.setText("This is tweeted using bot #firstTweet");
        
        try {
            tweety.postTweet(tweetRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
```
