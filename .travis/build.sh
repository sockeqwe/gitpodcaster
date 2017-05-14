#!/bin/sh
SLUG="sockeqwe/gitpodcaster"
BRANCH="master"

if [ "$TRAVIS_REPO_SLUG" != "$SLUG" ]; then
  echo "Skipping podcast generation. Expected '$SLUG' but was '$TRAVIS_REPO_SLUG'."
elif [ "$TRAVIS_BRANCH" = "$BRANCH" ]; then
  echo "Skipping podcast generation: directly push to master branch"
else
  ./gradlew build podcast
fi