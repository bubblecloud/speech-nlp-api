Speech NLP API
==============

Simple API for converting speech utterances to statements, questions and commands using natural language processing tools.

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

Using as a Dependency
---------------------

Maven:

```
<dependencies>
    <dependency>
        <groupId>org.bubblecloud.speech</groupId>
        <artifactId>speech-nlp-api-core</artifactId>
        <version>1.2</version>
    </dependency>
</dependencies>
```

External Dependencies
---------------------

Stanford CoreNLP is used as NLP pipeline. These libraries are included and must be added to local maven repository
manually as maven release of required version does not exist at time of writing:

http://stanfordnlp.github.io/CoreNLP/

Penn Treebank Tags
------------------

You can find out the tags used here:
http://web.mit.edu/6.863/www/PennTreebankTags.html
