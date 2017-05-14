#!/bin/sh
SLUG="sockeqwe/gitpodcaster"
BRANCH="master"

if [ "$TRAVIS_REPO_SLUG" != "$SLUG" ]; then
  echo "Skipping podcast generation. Expected '$SLUG' but was '$TRAVIS_REPO_SLUG'."
elif [ "$TRAVIS_BRANCH" = "$BRANCH" ]; then
  echo "Skipping podcast generation: directly push to master branch"
else
  git status
  git add .
  git commit -am "Genreated podcast stuff"
  git push origin $TRAVIS_BRANCH
  git status
fi