#!/bin/sh
SLUG="sockeqwe/gitpodcaster"
BRANCH="master"
COMMIT_MSG="Auto Genreated podcast stuff"

git status

if [ "$TRAVIS_REPO_SLUG" != "$SLUG" ]; then
  echo "Skipping podcast generation. Expected '$SLUG' but was '$TRAVIS_REPO_SLUG'."
#elif [ "$TRAVIS_PULL_REQUEST" != "true" ]; then
#  echo "Skipping podcast generation: was not a pull request."
elif [ "$TRAVIS_BRANCH" = "$BRANCH" ]; then
  echo "Skipping podcast generation: directly push to master branch: $TRAVIS_BRANCH"
elif [ "$TRAVIS_COMMIT_MESSAGE" = "$COMMIT_MSG" ]; then
  echo "Skipping podcast generation: push was caused by auto generated push"
else
  git checkout $TRAVIS_BRANCH
  git status
  git add .
  git commit -am "$COMMIT_MSG"
  git push origin $TRAVIS_BRANCH
  git status
fi