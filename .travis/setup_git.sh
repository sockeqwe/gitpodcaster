#!/bin/sh
git remote set-url origin https://sockeqwe:${GH_TOKEN}@github.com/sockeqwe/gitpodcaster.git
git config --global user.email "travis@travis-ci.org"
git config --global user.name "Travis CI"

git status