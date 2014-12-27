package lv.rigadevday.android.domain.mapper;

import com.google.common.collect.Lists;

import java.util.List;

import lv.rigadevday.android.common.StringUtils;
import lv.rigadevday.android.domain.Contact;
import lv.rigadevday.android.domain.reference.ContactType;
import lv.rigadevday.android.domain.Speaker;
import lv.rigadevday.android.domain.reference.SpeakerType;
import lv.rigadevday.android.domain.dto.ContactInfo;
import lv.rigadevday.android.domain.dto.SpeakerInfo;

public class SpeakerMapper {

    public Speaker getSpeaker(SpeakerInfo dto) {
        Speaker entity = new Speaker();

        entity.setUid(dto.getId());
        entity.setName(dto.getName());
        entity.setBio(dto.getBio());
        entity.setCompany(dto.getCompany());
        entity.setCountry(dto.getCountry());
        entity.setLineup(dto.getLineup());
        entity.setType(SpeakerType.byValue(dto.getType()));

        List<Contact> contacts = mapToContacts(dto.getContact());
        entity.setContacts(contacts);

        return entity;
    }

    private List<Contact> mapToContacts(ContactInfo contactInfo) {
        List<Contact> contacts = Lists.newArrayList();
        if (!StringUtils.isEmpty(contactInfo.getBlog())) {
            Contact blog = new Contact();
            blog.setType(ContactType.BLOG);
            blog.setValue(contactInfo.getBlog());
            contacts.add(blog);
        }
        if (!StringUtils.isEmpty(contactInfo.getTwitter())) {
            Contact blog = new Contact();
            blog.setType(ContactType.TWITTER);
            blog.setValue(contactInfo.getTwitter());
            contacts.add(blog);
        }
        return contacts;
    }
}