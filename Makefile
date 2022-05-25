build:
	docker build -t super-heroes-api .

down:
	docker stop super-heroes-api

up: build
	docker run --rm -d -p 8080:8080 --name super-heroes-api super-heroes-api:latest

output: build
	docker run --rm -d -p 8080:8080 --name super-heroes-api super-heroes-api:latest