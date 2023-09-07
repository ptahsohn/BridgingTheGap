
# Experiment #9 (Bridging The Gap)

## Table of Contents

1. [Overview](#Overview)
2. [Requirements](#Requirements)
   1. [1. Daraja 2.0 Account](#Daraja-2.0-Account)
   2. [2. Access Token](#Access-Token)
   3. [3. API Endpoint](#API-Endpoint)
3. [Procedure](#Procedure)
   1. [Setting Up The Dependencies](#Setting-Up-The-Dependencies)
   2. [Creating An App](#Creating-An-App)
   3. [Authorisation](#Authorisation)
   4. [Dynamic QR Code Generation](#Dynamic-QR-Code-Generation)
   5. [Testing](#Testing)
4. [Screenshot](#Screenshot)
5. [Trivia](#Trivia)

## Overview
This is an Android app that generates a dummy QR Code through the Daraja API that in a real world situation can be used to conduct MPESA Transactions using the MPESA app...

## Requirements
Here's a list of what you need to run the app:
### 1. Daraja 2.0 Account
The Daraja API requires that you have an account set up. If you do not have one, feel free to create it [here](https://developer.safaricom.co.ke/)

### 2. Access Token
This is a String value that will be appended to our API Request for authentication purposes and usually expires after 1 hour. This documentation will walk you through on hopw to access it...

### 3. API Endpoint
The Daraja API actually consists of different endpoints that offer various functionalities related to MPESA. To generate a dynamic MPESA QR Code, we will use the following endpoint: [https://sandbox.safaricom.co.ke/mpesa/qrcode/v1/generate](https://sandbox.safaricom.co.ke/mpesa/qrcode/v1/generate)

## Procedure