# Spreedly Java Client [![Build Status](https://travis-ci.org/CYFLabs/spreedly-java-client.svg?branch=master)](https://travis-ci.org/CYFLabs/spreedly-java-client)

You can sign up for a Spreedly account at [Spreedly](https://spreedly.com/).

Requirements
============

Java 1.6 and later.

Installation
============

### Maven users

Add this dependency to your project's POM:

    <dependency>
      <groupId>com.github.agseco</groupId>
      <artifactId>spreedly-java-client</artifactId>
      <version>0.0.1-SNAPSHOT</version>
    </dependency>

### Gradle users

Add this dependency to your project's build file:

    compile "com.github.agseco:spreedly-java-client:0.0.1-SNAPSHOT"

Usage
=====

Add sample usages.

Testing
=======

Add testing info.

Special Thanks
=======

To [Stripe](https://github.com/stripe/stripe-java) because we have used their implementation as our base.

Supported actions (8/38)
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
- Index
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
