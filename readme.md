# Fuel Consumption and Trip Cost Calculator

## Run with external Docker Images

Run pre-built Docker images from Docker Hub:

```shell
# pull
docker compose -f compose-deployment.yml pull

# start
docker compose -f compose-deployment.yml up -d

# stop
docker compose -f compose-deployment.yml down -v
```

Adding new languages is not possible when running external Docker images. It is only possible to save calculation results to the database.

### Build local Docker Images

Build local Docker images when developing:

```shell
# build
docker compose -f compose.yml build

# start
docker compose -f compose.yml up -d

# stop
docker compose -f compose.yml down -v
```

## Run Locally

1. Copy `.env.sample` to `.env` and configure database credentials. Create new database user if needed.
2. Initialize database by running `database_schema/01_db_script.sql` and `database_schema/02_localization_strings.sql`.
3. Grant permissions for user to the database.

## Add Language Localization

To add a new language, update three files (en_US localization as example):

#### Add to Database Script
Edit `database_schema/02_localization_strings.sql`:

```sql
INSERT INTO localization_strings(`key`, value, language) VALUES
('distance.label', 'Distance (km)', 'en_US'),
('consumption.label', 'Fuel Consumption (L/100 km)', 'en_US'),
('price.label', 'Fuel Price (per liter)', 'en_US'),
('calculate.button', 'Calculate Trip Cost', 'en_US'),
('result.label', 'Total fuel needed: {0} L | Total cost: {1}', 'en_US'),
('invalid.input', 'Invalid input', 'en_US');
```

#### Update UI in hello-view.fxml

Add a new button to switch to language:

```java
<Button fx:id="btnEN" text="English" onAction="#onLanguageEnglishButtonClick"/>
```

#### Update HelloController.java

Add a new FXML field and button action method:

```java
@FXML
public Button btnEN;

@FXML
public void onLanguageEnglishButtonClick(ActionEvent actionEvent) {
    this.currentLocale = new Locale("en", "US");
    setLanguage();
}
```
