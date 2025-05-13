INSERT INTO users (
  email,
  password_hash,
  first_name,
  last_name,
  birthdate,
  address
) VALUES
  (
    'alice@example.com',
    -- BCrypt("Str0ngP@ssw0rd2025!")
    '$2b$12$IvOoJvbCyDz27F9yy0irJO7fSmKMfkF/pKb5DMlpzbzWlb8XRMzLu',
    'Alice',
    'Anderson',
    '1990-01-15',
    '123 Main St'
  ),
  (
    'bob@example.com',
    -- BCrypt("C0mpl3xK3y!@")
    '$2b$12$0f6xumzsrETWO4O3SSscju0SYaXmIuNj0D6FtE3Ofm6ZJnYjWQbbm',
    'Bob',
    'Baker',
    '1985-06-20',
    '456 Oak Ave'
  ),
  (
    'dude@example.com',
    -- BCrypt("Unbr34k4bl3K!@")
    '$2b$12$4eP/k2Ly7qxDP5IKyc1bAOOn71TZaXgykR6DHSq1pANyy/nTJRF4W',
    'The',
    'Dude',
    '1990-05-05',
    '789 Elm St'
  );
