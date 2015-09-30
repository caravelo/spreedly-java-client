# Spreedly Java Client [![Build Status](https://travis-ci.org/caravelo/spreedly-java-client.svg?branch=master)](https://travis-ci.org/CYFLabs/spreedly-java-client)

You can sign up for a Spreedly account at [Spreedly](https://spreedly.com/).

Requirements
============

Java 1.7 and later.

Installation
============

### Maven users

Add this dependency to your project's POM:

    <dependency>
      <groupId>com.caravelo</groupId>
      <artifactId>spreedly-java-client</artifactId>
      <version>0.0.1-SNAPSHOT</version>
    </dependency>

### Gradle users

Add this dependency to your project's build file:

    compile "com.caravelo:spreedly-java-client:0.0.1-SNAPSHOT"

Usage
=====

Add sample usages. (TODO)

Testing
=======

Add testing info. (TODO)

Supported actions (9/38)
==================

### Gateways

- Authorize
- Create
- General Credit
- Index
- Options Index
- Purchase :white_check_mark:
- Redact
- Retain
- Show
- Store
- Transactions
- Update
- Verify :white_check_mark:

### Payment Methods

- Create (Transparent Redirect)
- Create (Authenticated API)
- Create (JavaScript API)
- Index :white_check_mark:
- Recache
- Redact :white_check_mark:
- Retain :white_check_mark:
- Show :white_check_mark:
- Transactions
- Update

### Transactions

- Capture
- Credit :white_check_mark:
- Index :white_check_mark:
- Purchase
- Show :white_check_mark:
- Transcript
- Void

### Receivers

- Create
- Deliver
- Index
- Redact
- Show
- Update

### Certificates

- Create
- Update
