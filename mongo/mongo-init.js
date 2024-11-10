db = db.getSiblingDB("tax-calculate");
db.createUser({
  user: "admin-user",
  pwd: "S3cret",
  roles: [
    {
      role: "readWrite",
      db: "tax-calculate",
    },
  ],
});

db.createCollection("transaction_logs");
