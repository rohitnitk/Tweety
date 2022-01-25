# Tweety
A Twitter-API library for JAVA.<br>
==============================<br>
Code for Authorization (Oauth 1) can be found here :[Authorization](https://github.com/rohitnitk/Tweety/blob/ec987305827f166445d39bd5c8f9035af740136d/src/main/java/com/twitter/api/oauth/Authorization.java) <br>
This api contain code for Twitter-Oauth V1, and make request to V2 endpoint using this. 
<br>Example-
```java
 
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

