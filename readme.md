# Sentiment Analysis API

A lightweight Spring Boot application that provides a REST API for text sentiment analysis. The application is containerized and includes production-ready Kubernetes deployment configurations with Prometheus monitoring.

## Features

- Simple REST API for sentiment analysis
- Returns positive, negative, or neutral sentiment based on text input
- Spring Boot Actuator for health checks and metrics
- Prometheus metrics integration
- Docker containerization with multi-stage builds
- Production-ready Kubernetes manifests
- Horizontal Pod Autoscaler (HPA) support
- Ingress configuration for external access

## Tech Stack

- **Java 17**
- **Spring Boot 4.0.1**
- **Spring Boot Actuator** - Health checks and monitoring
- **Micrometer Prometheus** - Metrics collection
- **Maven** - Build tool
- **Docker** - Containerization
- **Kubernetes** - Container orchestration

## Prerequisites

- Java 17 or higher
- Maven 3.x
- Docker (for containerization)
- Kubernetes cluster (for deployment)
- kubectl (for Kubernetes operations)

## Getting Started

### Local Development

#### Build the application

```bash
./mvnw clean package
```

#### Run tests

```bash
./mvnw test
```

#### Run the application

```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`.

### API Usage

#### Analyze sentiment

```bash
# Positive sentiment
curl "http://localhost:8080/api/sentiment?text=This is good"
# Response: {"sentiment":"positive"}

# Negative sentiment
curl "http://localhost:8080/api/sentiment?text=This is bad"
# Response: {"sentiment":"negative"}

# Neutral sentiment
curl "http://localhost:8080/api/sentiment?text=Hello world"
# Response: {"sentiment":"neutral"}
```

#### Health check

```bash
curl http://localhost:8080/actuator/health
```

#### Prometheus metrics

```bash
curl http://localhost:8080/actuator/prometheus
```

## Docker Deployment

### Build Docker image

```bash
docker build -t sentiment:1.0 .
```

### Run container

```bash
docker run -p 8080:8080 sentiment:1.0
```

### Test the containerized application

```bash
curl "http://localhost:8080/api/sentiment?text=This is amazing"
```

## Kubernetes Deployment

### Prerequisites

Ensure you have a running Kubernetes cluster and `kubectl` configured.

### Deploy the application

```bash
# Create namespace and deploy all resources
kubectl apply -f k8s/

# Verify deployment
kubectl get pods -n sentiment-app
kubectl get svc -n sentiment-app
```

### Available Kubernetes resources

- **Deployment** ([k8s/deployment.yaml](k8s/deployment.yaml)) - 3 replicas with rolling updates
- **Service** ([k8s/service.yaml](k8s/service.yaml)) - LoadBalancer and ClusterIP services
- **Ingress** ([k8s/ingress.yaml](k8s/ingress.yaml)) - External access configuration
- **HPA** ([k8s/hpa.yaml](k8s/hpa.yaml)) - Horizontal Pod Autoscaler
- **Prometheus** ([k8s/prometheus-values.yaml](k8s/prometheus-values.yaml)) - Monitoring configuration
- **Grafana** ([k8s/grafana-values.yaml](k8s/grafana-values.yaml)) - Dashboard configuration

### Access the application in Kubernetes

```bash
# Get the service external IP
kubectl get svc sentiment-analyzer-service -n sentiment-app

# Test the API (replace <EXTERNAL-IP> with actual IP)
curl "http://<EXTERNAL-IP>/api/sentiment?text=Kubernetes is great"
```

### Scale the deployment

```bash
kubectl scale deployment sentiment-analyzer -n sentiment-app --replicas=5
```

### View logs

```bash
kubectl logs -f deployment/sentiment-analyzer -n sentiment-app
```

### Delete the deployment

```bash
kubectl delete -f k8s/
```

## Configuration

### Application properties

Located at `src/main/resources/application.properties`:

- **Server Port**: 8080
- **Application Name**: orch-final
- **Actuator Endpoints**: health, prometheus, metrics

### Resource limits (Kubernetes)

- **CPU**: 100m (request) / 500m (limit)
- **Memory**: 128Mi (request) / 512Mi (limit)

## Monitoring

### Prometheus metrics

The application exposes Prometheus metrics at `/actuator/prometheus`. Metrics include:

- JVM metrics (memory, threads, GC)
- HTTP request metrics
- System metrics (CPU, file descriptors)

### Health checks

- **Liveness**: `/actuator/health`
- **Readiness**: `/actuator/health`

## Project Structure

```
.
├── Dockerfile                  # Multi-stage Docker build
├── pom.xml                    # Maven dependencies and build config
├── src/
│   └── main/
│       └── java/
│           └── mephi/
│               └── orchfinal/
│                   └── OrchFinalApplication.java  # Main application and controller
└── k8s/                       # Kubernetes manifests
    ├── deployment.yaml        # Application deployment
    ├── service.yaml          # Service definitions
    ├── ingress.yaml          # Ingress configuration
    ├── hpa.yaml              # Horizontal Pod Autoscaler
    ├── prometheus-values.yaml # Prometheus setup
    └── grafana-values.yaml   # Grafana setup
```

## API Reference

### Endpoint

`GET /api/sentiment`

### Parameters

| Parameter | Type   | Required | Description          |
|-----------|--------|----------|----------------------|
| text      | string | No       | Text to analyze (default: "") |

### Response

```json
{
  "sentiment": "positive|negative|neutral"
}
```

### Sentiment logic

- **Positive**: Text contains "good"
- **Negative**: Text contains "bad"
- **Neutral**: All other cases

## Development

### Maven commands

```bash
# Clean build
./mvnw clean

# Compile
./mvnw compile

# Run tests
./mvnw test

# Package
./mvnw package

# Skip tests
./mvnw package -DskipTests
```

## License

This project is part of the SkillFactory orchestration course.

## Contributing

This is an educational project. For questions or improvements, please contact the repository maintainer.
