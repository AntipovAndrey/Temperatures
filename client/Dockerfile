FROM node:10.14.0

RUN mkdir -p /client/app
WORKDIR /client/app

COPY . /client/app

RUN npm install --silent

ENTRYPOINT ["npm", "start"]
