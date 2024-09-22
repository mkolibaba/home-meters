FROM clojure:lein
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
FROM clojure:lein
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
# Load deps into build, should auto fetch new deps
COPY project.clj /usr/src/app/
RUN lein deps
COPY . /usr/src/app
EXPOSE 3000
CMD lein ring server