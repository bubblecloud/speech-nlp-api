Speech NLP API
==============

Simple API for converting speech utterances to statements, questions and commands using natural language processing tools.

Currently only en_US locale is supported.

Using Public Cloud Deployment
-----------------------------

```
final SpeechNlpHttpClient client = new SpeechNlpHttpClient("https://speech-nlp-api.herokuapp.com/");
final UtteranceAnalysisResult result = client.analyseUtterance("en_US", "kindly open the red door");
System.out.println(result);
```

Prints out:

```
UtteranceAnalysis of a COMMAND
utterance >>>
ROOT
 S
  ADVP
   RB
    kindly
  VP
   VB
    open
   NP
    DT
     the
    JJ
     red
    NN
     door

verb phrase >>>
   VB
    open

object phrase >>>
   NP
    DT
     the
    JJ
     red
    NN
     door
```

Maven Repository
----------------

Serial-comm and zigbee4java dependencies can be found from the following repository for convenience.

```
<repositories>
    <repository>
        <id>bubblecloud-cloudbees-release</id>
        <url>http://repository-bubblecloud.forge.cloudbees.com/release/</url>
    </repository>
</repositories>
```

Maven Dependency for Speech NLP Client
--------------------------------------

Maven:

```
<dependencies>
    <dependency>
        <groupId>org.bubblecloud.speech</groupId>
        <artifactId>speech-nlp-client</artifactId>
        <version>1.4</version>
    </dependency>
</dependencies>
```

Maven Dependency for Speech NLP API Core
----------------------------------------

Maven:

```
<dependencies>
    <dependency>
        <groupId>org.bubblecloud.speech</groupId>
        <artifactId>speech-nlp-api/core</artifactId>
        <version>1.3</version>
    </dependency>
</dependencies>
```

External Dependencies
---------------------

Stanford CoreNLP is used as NLP pipeline:

http://stanfordnlp.github.io/CoreNLP/

Penn Treebank Tags
------------------

You can find out the parse tree tags used here:
http://web.mit.edu/6.863/www/PennTreebankTags.html
