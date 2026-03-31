## ADDED Requirements

### Requirement: The Fork Must Present Itself As obodc
The system documentation and top-level release metadata SHALL present the forked product as `obodc` rather than ODC.

#### Scenario: User lands on repository root
- **WHEN** a user opens the repository root README
- **THEN** the project SHALL be introduced as `obodc`

#### Scenario: User reads release metadata
- **WHEN** a user inspects the current fork version and changelog header
- **THEN** the version line SHALL identify the fork release as `obodc`

### Requirement: Contributor Documentation Must Point To The Fork By Default
Contributor-facing documentation SHALL reference `Minorli/obodc` as the default code repository.

#### Scenario: Developer follows clone instructions
- **WHEN** a developer reads setup instructions in the fork documentation
- **THEN** the clone URL SHALL point to `Minorli/obodc`

#### Scenario: Developer reads contribution instructions
- **WHEN** a developer reads contribution guidance
- **THEN** the repository and issue references SHALL default to the fork unless the text is explicitly about upstream

### Requirement: Inherited Upstream History Must Be Explicitly Framed
Inherited ODC history SHALL remain available, but it SHALL be labeled as inherited upstream history.

#### Scenario: User reads old release notes
- **WHEN** a user reads changelog entries predating the fork
- **THEN** the changelog SHALL make clear those entries were inherited from upstream ODC rather than created under obodc
