package food.booking.app.shared.persistence;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Base persistent enumeration user type
 *
 * @param <T> type
 * @author shazam2morrow
 */
public abstract class BasePersistentEnumUserType<T extends PersistentEnum> implements UserType {

    @Override
    public abstract Class<T> returnedClass();

    @Override
    public int[] sqlTypes() {
        return new int[]{Types.SMALLINT};
    }

    @Override
    public boolean equals(Object first, Object second) throws HibernateException {
        return first == second;
    }

    @Override
    public int hashCode(Object object) throws HibernateException {
        return object == null ? 0 : object.hashCode();
    }

    @Override
    public Object nullSafeGet(
            ResultSet rs,
            String[] names,
            SharedSessionContractImplementor implementor,
            Object object) throws HibernateException, SQLException {
        short id = rs.getShort(names[0]);
        if (rs.wasNull()) {
            return null;
        }
        for (PersistentEnum persistentEnum : returnedClass().getEnumConstants()) {
            if (id == persistentEnum.getId()) {
                return persistentEnum;
            }
        }
        throw new IllegalStateException("Uknown " + returnedClass().getSimpleName() + " id");
    }

    @Override
    public void nullSafeSet(
            PreparedStatement ps,
            Object value,
            int index,
            SharedSessionContractImplementor implementor) throws HibernateException, SQLException {
        if (value == null) {
            ps.setNull(index, Types.SMALLINT);
        } else {
            ps.setShort(index, ((T) value).getId());
        }
    }

    @Override
    public Object deepCopy(Object object) throws HibernateException {
        return object;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object object) throws HibernateException {
        return (Serializable) object;
    }

    @Override
    public Object assemble(Serializable serializable, Object object) throws HibernateException {
        return object;
    }

    @Override
    public Object replace(Object object, Object o1, Object o2) throws HibernateException {
        return object;
    }

}
