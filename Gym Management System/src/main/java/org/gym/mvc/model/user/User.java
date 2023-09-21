package org.gym.mvc.model.user;

public abstract class User {
    private final Aliases[] aliases;
    private final ActorType actorType;
    private final ContactPerson contactPerson;
    private final String contactDetails;

    public User(Aliases[] aliases,
                ActorType actorType,
                ContactPerson contactPerson,
                String contactDetails,
                String username,
                String password,
                String firstName,
                String lastName,
                String address,
                String city,
                String province,
                int postalCode,
                int age,
                Gender sex)
    {
        this.aliases = aliases;
        this.actorType = actorType;
        this.contactPerson = contactPerson;
        this.contactDetails = contactDetails;
    }

    public Aliases[] getAliases()
    {
        return aliases;
    }

    public ActorType getActorType()
    {
        return actorType;
    }

    public ContactPerson getContactPerson()
    {
        return contactPerson;
    }

    public String getContactDetails()
    {
        return contactDetails;
    }

    public abstract void performAction();

    @Override public String toString()
    {
        return "User:" + System.lineSeparator() + "Aliases->" + getAliasesString() +
               System.lineSeparator() + "Actor Type->" + actorType + System.lineSeparator() +
               "Contact Person->" + contactPerson + System.lineSeparator() + "Contact Details->" +
               contactDetails;
    }

    private String getAliasesString()
    {
        StringBuilder sb = new StringBuilder();
        Aliases[] aliasesArray = getAliases();
        for (int i = 0; i < aliasesArray.length; i++) {
            sb.append(aliasesArray[i].name());
            if (i < aliasesArray.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}