# Project-Web-Java

## Tests, artifacts and CI

 - **Run tests locally:**

	 ```bash
	 cd Project
	 ./mvnw -f Project test
	 ```

 - **Built artifact & reports:**
	 - The project JAR is produced at `Project/target/Project-0.0.1-SNAPSHOT.jar`.
	 - Test reports are generated at `Project/target/surefire-reports/`.

 - **Release with artifacts:**
	 - I published a release containing `artifacts.zip` (JAR + `surefire-reports`). Download from the repository releases page.

 - **CI:**
	 - A GitHub Actions workflow is included at `.github/workflows/maven.yml` to run `./mvnw -f Project test` on push and pull requests.

If you want, I can add a CI status badge to this README or attach the artifacts to the open PR.
