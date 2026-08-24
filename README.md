# Currency Converter API

A RESTful Spring Boot API for converting currencies using live exchange rates, with API key authentication and persistent conversion history.

## Features

- Convert amounts between currencies using real-time exchange rates (via the [Frankfurter API](https://frankfurter.dev))
- List all supported currencies
- View conversion history, persisted in an H2 database
- API key–based authentication on all endpoints

## Tech Stack

- **Java 21**, **Spring Boot 4.1**
- **Spring Web** – REST API layer
- **Spring WebFlux (WebClient)** – external API integration
- **Spring Data JPA** + **H2** – persistence
- **Spring Security** – custom API key authentication filter
- **JUnit 5** + **Mockito** – unit testing
- **Maven**

## API Endpoints

| Method | Endpoint          | Description                          |
|--------|-------------------|---------------------------------------|
| POST   | `/api/convert`    | Convert an amount between currencies |
| GET    | `/api/currencies` | List all supported currencies        |
| GET    | `/api/history`    | Retrieve saved conversion history    |

All endpoints require an `X-API-Key` header.

### Example request

\`\`\`bash
curl -X POST http://localhost:8080/api/convert \
  -H "Content-Type: application/json" \
  -H "X-API-Key: your-api-key" \
  -d '{"fromCurrency":"USD","toCurrency":"INR","amount":100}'
\`\`\`

### Example response

\`\`\`json
{
  "fromCurrency": "USD",
  "toCurrency": "INR",
  "originalAmount": 100,
  "convertedAmount": 9570.00,
  "exchangeRate": 95.70
}
\`\`\`

## Running Locally

1. Clone the repository
   \`\`\`bash
   git clone https://github.com/sandyk118176-ai/currency-converter-api.git
   cd currency-converter-api
   \`\`\`
2. Set your API key in `src/main/resources/application.properties`:
   \`\`\`properties
   app.api-key=your-secret-key
   \`\`\`
3. Run the application
   \`\`\`bash
   ./mvnw spring-boot:run
   \`\`\`
4. The API will be available at `http://localhost:8080`

## Running Tests

\`\`\`bash
./mvnw test
\`\`\`

## Notes

- Uses an in-memory H2 database — data resets on every restart
- H2 console available at `/h2-console` (no API key required) for inspecting the database during development