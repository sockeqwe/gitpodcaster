#!/bin/sh
SLUG="sockeqwe/gitpodcaster"
BRANCH="master"

if [ "$TRAVIS_REPO_SLUG" != "$SLUG" ]; then
  echo "Skipping snapshot deployment: wrong repository. Expected '$SLUG' but was '$TRAVIS_REPO_SLUG'."
elif [ "$TRAVIS_BRANCH" == "$BRANCH" ]; then
  echo "Skipping snapshot deployment: was not a pull request."
else
  git status
  git add .
  git commit -am "Genreated podcast stuff"
  git push
  git status
fi