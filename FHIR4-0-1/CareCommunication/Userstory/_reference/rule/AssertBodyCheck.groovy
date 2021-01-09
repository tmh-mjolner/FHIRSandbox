/*
 rule.summary=Test of XSD validation
 rule.description=static XSD validation
*/


import javax.xml.XMLConstants
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.SchemaFactory
String xmlString  =
'''<?xml version="1.0" encoding="UTF-8"?>
<VANSEnvelope xmlns="urn:oio:medcom:vans-envelope:1.0.4">
  <SenderID EndPointType="EAN">5790001659592</SenderID>
  <ReceiverID EndPointType="EAN">5790000121526</ReceiverID>
  <EnvelopeIdentifier>068dadff-12bb-406d-8c86-083974dc0180</EnvelopeIdentifier>
  <SentDateTime>2020-08-26T17:12:13</SentDateTime>
  <Message>
    <MetaInformation>
      <Identifier>068dadff-12bb-406d-8c86-083974dc0180</Identifier>
      <Document>
        <Format>XML</Format>
        <Name>MCM:XDIS22</Name>
        <Version>XD2230L</Version>
        <SizeInBytes>6089</SizeInBytes>
      </Document>
      <Transport>
        <Type>reliable</Type>
        <TransformMessage>false</TransformMessage>
        <ServiceTag name="MCM:STATISTIC">manual</ServiceTag>
      </Transport>
    </MetaInformation>
    <Data>PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPEVtZXNzYWdlIHhtbG5zPSJodHRwOi8vcmVwLm9pby5kay9tZWRjb20uZGsveG1sL3NjaGVtYXMvMjAxOS8wMy8zMS8iPgogIDxFbnZlbG9wZT4KICAgIDxTZW50PgogICAgICA8RGF0ZT4yMDIwLTA4LTI2PC9EYXRlPgogICAgICA8VGltZT4xNzoxMjwvVGltZT4KICAgIDwvU2VudD4KICAgIDxJZGVudGlmaWVyPlhCZjBTTTRUeEZvdlFGPC9JZGVudGlmaWVyPgogICAgPEFja25vd2xlZGdlbWVudENvZGU+cGx1c3Bvc2l0aXZrdml0dDwvQWNrbm93bGVkZ2VtZW50Q29kZT4KICA8L0VudmVsb3BlPgogIDxFbWVyZ2VuY3lNdW5pY2lwYWxpdHlMZXR0ZXI+CiAgICA8TGV0dGVyPgogICAgICA8SWRlbnRpZmllcj5YQmYwU000VHhGb3ZRRjwvSWRlbnRpZmllcj4KICAgICAgPFZlcnNpb25Db2RlPlhEMjIzMEw8L1ZlcnNpb25Db2RlPgogICAgICA8U3RhdGlzdGljYWxDb2RlPlhESVMyMjwvU3RhdGlzdGljYWxDb2RlPgogICAgICA8QXV0aG9yaXNhdGlvbj4KICAgICAgICA8RGF0ZT4yMDIwLTA4LTI2PC9EYXRlPgogICAgICAgIDxUaW1lPjE3OjEyPC9UaW1lPgogICAgICA8L0F1dGhvcmlzYXRpb24+CiAgICAgIDxUeXBlQ29kZT5YRElTMjI8L1R5cGVDb2RlPgogICAgICA8U3RhdHVzQ29kZT5ueXRicmV2PC9TdGF0dXNDb2RlPgogICAgICA8RXBpc29kZU9mQ2FyZUlkZW50aWZpZXI+MzlkYWRhY2M4NzYxNGQwZTk2ZjBmMjAyYjI0YTc5YzA8L0VwaXNvZGVPZkNhcmVJZGVudGlmaWVyPgogICAgPC9MZXR0ZXI+CiAgICA8U2VuZGVyPgogICAgICA8RUFOSWRlbnRpZmllcj41NzkwMDAxNjU5NTkyPC9FQU5JZGVudGlmaWVyPgogICAgICA8SWRlbnRpZmllcj40NjE8L0lkZW50aWZpZXI+CiAgICAgIDxJZGVudGlmaWVyQ29kZT5rb21tdW5lbnVtbWVyPC9JZGVudGlmaWVyQ29kZT4KICAgICAgPE9yZ2FuaXNhdGlvbk5hbWU+T2RlbnNlPC9PcmdhbmlzYXRpb25OYW1lPgogICAgICA8RGVwYXJ0bWVudE5hbWU+U3VuZGhlZG9nZm9yZWJ5Z2dlbHNlPC9EZXBhcnRtZW50TmFtZT4KICAgICAgPFN0cmVldE5hbWU+TcOlZ2ViYWtrZW4gMTA8L1N0cmVldE5hbWU+CiAgICAgIDxEaXN0cmljdE5hbWU+T2RlbnNlPC9EaXN0cmljdE5hbWU+CiAgICAgIDxQb3N0Q29kZUlkZW50aWZpZXI+NTI1MDwvUG9zdENvZGVJZGVudGlmaWVyPgogICAgPC9TZW5kZXI+CiAgICA8UmVjZWl2ZXI+CiAgICAgIDxFQU5JZGVudGlmaWVyPjU3OTAwMDAxMjE1MjY8L0VBTklkZW50aWZpZXI+CiAgICAgIDxJZGVudGlmaWVyPjA3OTc0MTwvSWRlbnRpZmllcj4KICAgICAgPElkZW50aWZpZXJDb2RlPnlkZXJudW1tZXI8L0lkZW50aWZpZXJDb2RlPgogICAgICA8T3JnYW5pc2F0aW9uTmFtZT5sw6ZnZTwvT3JnYW5pc2F0aW9uTmFtZT4KICAgICAgPERlcGFydG1lbnROYW1lPsOGbGRyZWJvcmdlcmU8L0RlcGFydG1lbnROYW1lPgogICAgICA8U3RyZWV0TmFtZT5WYW5kdsOmcmtzdmVqIDk5PC9TdHJlZXROYW1lPgogICAgICA8RGlzdHJpY3ROYW1lPkhpbGxlcsO4ZDwvRGlzdHJpY3ROYW1lPgogICAgICA8UG9zdENvZGVJZGVudGlmaWVyPjM0MDA8L1Bvc3RDb2RlSWRlbnRpZmllcj4KICAgIDwvUmVjZWl2ZXI+CiAgICA8UGF0aWVudD4KICAgICAgPENpdmlsUmVnaXN0cmF0aW9uTnVtYmVyPjMxMDU3MzQ5NjM8L0NpdmlsUmVnaXN0cmF0aW9uTnVtYmVyPgogICAgICA8UGVyc29uU3VybmFtZU5hbWU+S2xpbnQ8L1BlcnNvblN1cm5hbWVOYW1lPgogICAgICA8UGVyc29uR2l2ZW5OYW1lPk1vZ2VuczwvUGVyc29uR2l2ZW5OYW1lPgogICAgICA8U3RyZWV0TmFtZT5WYWxreXJpZWdhZGUgNS4gMyBzYWw8L1N0cmVldE5hbWU+CiAgICAgIDxEaXN0cmljdE5hbWU+S8O4YmVuaGF2bjwvRGlzdHJpY3ROYW1lPgogICAgICA8UG9zdENvZGVJZGVudGlmaWVyPjEyMTQ8L1Bvc3RDb2RlSWRlbnRpZmllcj4KICAgIDwvUGF0aWVudD4KICAgIDxSZWxhdGl2ZT4KICAgICAgPFJlbGF0aW9uQ29kZT5tb3I8L1JlbGF0aW9uQ29kZT4KICAgICAgPFBlcnNvbklkZW50aWZpZXI+MjkxMTgyOTk5NjwvUGVyc29uSWRlbnRpZmllcj4KICAgICAgPFBlcnNvblN1cm5hbWVOYW1lPkJlcmdncmVuPC9QZXJzb25TdXJuYW1lTmFtZT4KICAgICAgPFBlcnNvbkdpdmVuTmFtZT5LaXJzdGVuIFRlc3Q8L1BlcnNvbkdpdmVuTmFtZT4KICAgICAgPFRlbGVwaG9uZVN1YnNjcmliZXI+CiAgICAgICAgPFRlbGVwaG9uZVN1YnNjcmliZXJJZGVudGlmaWVyPjQ0NDQ0NDQ0PC9UZWxlcGhvbmVTdWJzY3JpYmVySWRlbnRpZmllcj4KICAgICAgICA8VGVsZXBob25lU3Vic2NyaWJlckNvZGU+bW9iaWw8L1RlbGVwaG9uZVN1YnNjcmliZXJDb2RlPgogICAgICA8L1RlbGVwaG9uZVN1YnNjcmliZXI+CiAgICA8L1JlbGF0aXZlPgogICAgPFJlbGF0aXZlPgogICAgICA8UmVsYXRpb25Db2RlPmFlZ3RlZmFlbGxlPC9SZWxhdGlvbkNvZGU+CiAgICAgIDxQZXJzb25JZGVudGlmaWVyPjI1MTI0ODk5OTY8L1BlcnNvbklkZW50aWZpZXI+CiAgICAgIDxQZXJzb25TdXJuYW1lTmFtZT5CZXJnZ3JlbjwvUGVyc29uU3VybmFtZU5hbWU+CiAgICAgIDxQZXJzb25HaXZlbk5hbWU+TmFuY3kgQW5uIFRlc3Q8L1BlcnNvbkdpdmVuTmFtZT4KICAgICAgPFRlbGVwaG9uZVN1YnNjcmliZXI+CiAgICAgICAgPFRlbGVwaG9uZVN1YnNjcmliZXJJZGVudGlmaWVyPjMzMzMzMzMzPC9UZWxlcGhvbmVTdWJzY3JpYmVySWRlbnRpZmllcj4KICAgICAgICA8VGVsZXBob25lU3Vic2NyaWJlckNvZGU+YXJiZWpkZTwvVGVsZXBob25lU3Vic2NyaWJlckNvZGU+CiAgICAgIDwvVGVsZXBob25lU3Vic2NyaWJlcj4KICAgICAgPFRlbGVwaG9uZVN1YnNjcmliZXI+CiAgICAgICAgPFRlbGVwaG9uZVN1YnNjcmliZXJJZGVudGlmaWVyPjIyMjIyMjIyPC9UZWxlcGhvbmVTdWJzY3JpYmVySWRlbnRpZmllcj4KICAgICAgICA8VGVsZXBob25lU3Vic2NyaWJlckNvZGU+ZmFzdG5ldDwvVGVsZXBob25lU3Vic2NyaWJlckNvZGU+CiAgICAgIDwvVGVsZXBob25lU3Vic2NyaWJlcj4KICAgIDwvUmVsYXRpdmU+CiAgICA8UmVsYXRpdmU+CiAgICAgIDxSZWxhdGlvbkNvZGU+ZGF0dGVyPC9SZWxhdGlvbkNvZGU+CiAgICAgIDxQZXJzb25JZGVudGlmaWVyPjAyMDE2MDk5OTY8L1BlcnNvbklkZW50aWZpZXI+CiAgICAgIDxQZXJzb25TdXJuYW1lTmFtZT5MYXVyaWRzZW48L1BlcnNvblN1cm5hbWVOYW1lPgogICAgICA8UGVyc29uR2l2ZW5OYW1lPkxvbm5pIFRlc3Q8L1BlcnNvbkdpdmVuTmFtZT4KICAgIDwvUmVsYXRpdmU+CiAgICA8UmVsYXRpdmU+CiAgICAgIDxSZWxhdGlvbkNvZGU+ZmFyPC9SZWxhdGlvbkNvZGU+CiAgICAgIDxQZXJzb25TdXJuYW1lTmFtZT5CZXJnZ3JlbjwvUGVyc29uU3VybmFtZU5hbWU+CiAgICAgIDxQZXJzb25HaXZlbk5hbWU+RWxtZXIgVGVzdDwvUGVyc29uR2l2ZW5OYW1lPgogICAgPC9SZWxhdGl2ZT4KICAgIDxSZWxhdGl2ZT4KICAgICAgPFJlbGF0aW9uQ29kZT5zYW1sZXZlbmRlPC9SZWxhdGlvbkNvZGU+CiAgICAgIDxQZXJzb25TdXJuYW1lTmFtZT5CZXJnZ3JlbjwvUGVyc29uU3VybmFtZU5hbWU+CiAgICAgIDxUZWxlcGhvbmVTdWJzY3JpYmVyPgogICAgICAgIDxUZWxlcGhvbmVTdWJzY3JpYmVySWRlbnRpZmllcj45OTk5OTk5OTwvVGVsZXBob25lU3Vic2NyaWJlcklkZW50aWZpZXI+CiAgICAgICAgPFRlbGVwaG9uZVN1YnNjcmliZXJDb2RlPm1vYmlsPC9UZWxlcGhvbmVTdWJzY3JpYmVyQ29kZT4KICAgICAgPC9UZWxlcGhvbmVTdWJzY3JpYmVyPgogICAgICA8VGVsZXBob25lU3Vic2NyaWJlcj4KICAgICAgICA8VGVsZXBob25lU3Vic2NyaWJlcklkZW50aWZpZXI+ODg4ODg4ODg8L1RlbGVwaG9uZVN1YnNjcmliZXJJZGVudGlmaWVyPgogICAgICAgIDxUZWxlcGhvbmVTdWJzY3JpYmVyQ29kZT5hcmJlamRlPC9UZWxlcGhvbmVTdWJzY3JpYmVyQ29kZT4KICAgICAgPC9UZWxlcGhvbmVTdWJzY3JpYmVyPgogICAgICA8VGVsZXBob25lU3Vic2NyaWJlcj4KICAgICAgICA8VGVsZXBob25lU3Vic2NyaWJlcklkZW50aWZpZXI+Nzc3Nzc3Nzc8L1RlbGVwaG9uZVN1YnNjcmliZXJJZGVudGlmaWVyPgogICAgICAgIDxUZWxlcGhvbmVTdWJzY3JpYmVyQ29kZT5hcmJlamRlPC9UZWxlcGhvbmVTdWJzY3JpYmVyQ29kZT4KICAgICAgPC9UZWxlcGhvbmVTdWJzY3JpYmVyPgogICAgPC9SZWxhdGl2ZT4KICAgIDxSZWZlcnJhbD4KICAgICAgPElkZW50aWZpZXI+U0ozMzMzMTg8L0lkZW50aWZpZXI+CiAgICAgIDxSZWNlaXZlZD4KICAgICAgICA8RGF0ZT4yMDIwLTA4LTI2PC9EYXRlPgogICAgICAgIDxUaW1lPjE3OjExPC9UaW1lPgogICAgICA8L1JlY2VpdmVkPgogICAgICA8UmVmZXI+CiAgICAgICAgPERpYWdub3NlQ29kZT5FODY8L0RpYWdub3NlQ29kZT4KICAgICAgICA8RGlhZ25vc2VUeXBlQ29kZT5TS1NkaWFnbm9zZWtvZGU8L0RpYWdub3NlVHlwZUNvZGU+CiAgICAgICAgPERpYWdub3NlVGV4dD5VZHTDuHJyaW5nIG9nIG5lZHNhdCBla3N0cmFjZWxsdWzDpnJ2b2x1bWVuPC9EaWFnbm9zZVRleHQ+CiAgICAgIDwvUmVmZXI+CiAgICAgIDxSZWZlcnJhbEFkZGl0aW9uYWw+CiAgICAgICAgPERpYWdub3NlQ29kZT5ERTY1OTwvRGlhZ25vc2VDb2RlPgogICAgICAgIDxEaWFnbm9zZVR5cGVDb2RlPlNLU2RpYWdub3Nla29kZTwvRGlhZ25vc2VUeXBlQ29kZT4KICAgICAgICA8RGlhZ25vc2VUZXh0PkZlZG1lIHUuIHNwZWM8L0RpYWdub3NlVGV4dD4KICAgICAgPC9SZWZlcnJhbEFkZGl0aW9uYWw+CiAgICAgIDxSZWZlcnJhbEFkZGl0aW9uYWw+CiAgICAgICAgPERpYWdub3NlQ29kZT5ERTY1ODwvRGlhZ25vc2VDb2RlPgogICAgICAgIDxEaWFnbm9zZVR5cGVDb2RlPlNLU2RpYWdub3Nla29kZTwvRGlhZ25vc2VUeXBlQ29kZT4KICAgICAgICA8RGlhZ25vc2VUZXh0PkFuZGVuIGxva2FsaXNlcmV0IGZlZG1lPC9EaWFnbm9zZVRleHQ+CiAgICAgIDwvUmVmZXJyYWxBZGRpdGlvbmFsPgogICAgICA8UmVmZXJyYWxBZGRpdGlvbmFsPgogICAgICAgIDxEaWFnbm9zZUNvZGU+REU2NTk8L0RpYWdub3NlQ29kZT4KICAgICAgICA8RGlhZ25vc2VUeXBlQ29kZT5TS1NkaWFnbm9zZWtvZGU8L0RpYWdub3NlVHlwZUNvZGU+CiAgICAgICAgPERpYWdub3NlVGV4dD5Mb2thbGlzZXJldCBmZWRtZSBVTlM8L0RpYWdub3NlVGV4dD4KICAgICAgPC9SZWZlcnJhbEFkZGl0aW9uYWw+CiAgICAgIDxSZWZlcnJhbEFkZGl0aW9uYWw+CiAgICAgICAgPERpYWdub3NlQ29kZT5ERTY1OEE8L0RpYWdub3NlQ29kZT4KICAgICAgICA8RGlhZ25vc2VUeXBlQ29kZT5TS1NkaWFnbm9zZWtvZGU8L0RpYWdub3NlVHlwZUNvZGU+CiAgICAgICAgPERpYWdub3NlVGV4dD5BYmRvbWVuIHBlbmRlbnM8L0RpYWdub3NlVGV4dD4KICAgICAgPC9SZWZlcnJhbEFkZGl0aW9uYWw+CiAgICAgIDxSZWZlcnJhbEFkZGl0aW9uYWw+CiAgICAgICAgPERpYWdub3NlQ29kZT5SNjM8L0RpYWdub3NlQ29kZT4KICAgICAgICA8RGlhZ25vc2VUeXBlQ29kZT5TS1NkaWFnbm9zZWtvZGU8L0RpYWdub3NlVHlwZUNvZGU+CiAgICAgICAgPERpYWdub3NlVGV4dD5TeW1wdG9tZXIgb2cgYWJub3JtZSBmdW5kIHZlZHLDuHJlbmRlIGbDuGRlLSBvZyB2w6Zza2VpbmR0YWdlbHNlPC9EaWFnbm9zZVRleHQ+CiAgICAgIDwvUmVmZXJyYWxBZGRpdGlvbmFsPgogICAgPC9SZWZlcnJhbD4KICAgIDxDbGluaWNhbEluZm9ybWF0aW9uPgogICAgICA8U2lnbmVkPgogICAgICAgIDxEYXRlPjIwMjAtMDgtMjY8L0RhdGU+CiAgICAgICAgPFNpZ25lZEJ5PkhlaWRpIEFkbWluIE5vcm1hbjwvU2lnbmVkQnk+CiAgICAgICAgPEpvYlRpdGxlPlZpc2l0YXRvcjwvSm9iVGl0bGU+CiAgICAgIDwvU2lnbmVkPgogICAgICA8VGV4dDAxPkRldHRlIGVyIGVuIHRlc3QgYWYgc3RlcCAzLjEgZm9yIFhESVMyMiBwcm90b2tvbGxlbi4gRGV0dGUgZXIgZXQgIm55dCIgYWZzbHV0bmluZ3Nub3RhdCBzb20gYmVzdmFyZWxzZSB0aWwgZW4gaGVudmlzbmluZy48L1RleHQwMT4KICAgIDwvQ2xpbmljYWxJbmZvcm1hdGlvbj4KICA8L0VtZXJnZW5jeU11bmljaXBhbGl0eUxldHRlcj4KPC9FbWVzc2FnZT4=</Data>
  </Message>
</VANSEnvelope>
'''
String xsdString =
'''<?xml version="1.0" encoding="UTF-8"?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema" targetNamespace="urn:oio:medcom:vans-envelope:1.0.4"
  xmlns:vans="urn:oio:medcom:vans-envelope:1.0.4" elementFormDefault="qualified" attributeFormDefault="unqualified">

  <xs:element name="VANSEnvelope" type="vans:VANSEnvelopeType"/>
  
  <xs:complexType name="VANSEnvelopeType">
    <xs:sequence>
      <xs:element name="SenderID" type="vans:EndPointType"/>
      <xs:element name="ReceiverID" type="vans:EndPointType"/>
      <xs:element name="EnvelopeIdentifier" type="vans:UUIDType"/>
      <xs:element name="SentDateTime" type="xs:dateTime"/>
      <xs:choice>
        <xs:element name="Message" type="vans:MessageType"/>
        <xs:element name="Receipt" type="vans:ReceiptType"/>
      </xs:choice>
    </xs:sequence>
  </xs:complexType>

  <xs:complexType name="EndPointType">
    <xs:simpleContent>
      <xs:extension base="vans:EndPointValueType">
        <xs:attribute name="EndPointType" use="required">
          <xs:simpleType>
            <xs:restriction base="xs:string">
              <xs:enumeration value="EAN"/>
              <xs:enumeration value="CVR"/>
              <xs:enumeration value="VANS"/>
            </xs:restriction>
          </xs:simpleType>
        </xs:attribute>
      </xs:extension>
    </xs:simpleContent>
  </xs:complexType>

  <xs:simpleType name="EndPointValueType">
    <xs:restriction base="xs:token">
      <xs:maxLength value="18"/>
      <xs:minLength value="1"/>
    </xs:restriction>
  </xs:simpleType>

  <xs:simpleType name="UUIDType">
    <xs:restriction base="xs:string">
      <xs:pattern value="[aA-fF0-9]{8}-[aA-fF0-9]{4}-[aA-fF0-9]{4}-[aA-fF0-9]{4}-[aA-fF0-9]{12}"/>
    </xs:restriction>
  </xs:simpleType>

  <xs:complexType name="MessageType">
    <xs:sequence>
      <xs:element name="MetaInformation" type="vans:MetaInformationType"/>
      <xs:element name="Data" type="xs:base64Binary"/>
    </xs:sequence>
  </xs:complexType>
  
  <xs:complexType name="ProcessingType">
    <xs:sequence>
      <xs:element name="ProviderIdentifier" type="vans:TextType"/>
      <xs:element name="ServiceIdentifier" type="vans:TextType"/>
    </xs:sequence>
  </xs:complexType>

  <xs:complexType name="DocumentType">
    <xs:sequence>
      <xs:element name="Format" type="vans:ContentType"/>
      <xs:element name="Name" type="vans:TextType"/>
      <xs:element name="Version" type="vans:TextType" minOccurs="0"/>
      <xs:element name="SizeInBytes" type="xs:int"/>
    </xs:sequence>
  </xs:complexType>
  
  <xs:complexType name="TransportType">
    <xs:sequence>
      <xs:element name="Type" minOccurs="0">
        <xs:simpleType>
          <xs:restriction base="xs:string">
            <xs:enumeration value="reliable"/>
            <xs:enumeration value="unreliable"/>
          </xs:restriction>
        </xs:simpleType>
      </xs:element>
      <xs:element name="TransformMessage" type="xs:boolean"/>
      <xs:element name="ServiceTag" type="vans:ServiceTagType" maxOccurs="5" minOccurs="0"/>
    </xs:sequence>
  </xs:complexType>

  <xs:complexType name="MetaInformationType">
    <xs:sequence>
      <xs:element name="Identifier" type="vans:UUIDType"/>
      <xs:element name="Processing" type="vans:ProcessingType" minOccurs="0"/>
      <xs:element name="Document" type="vans:DocumentType"/>
      <xs:element name="Transport" type="vans:TransportType" minOccurs="0"/>
    </xs:sequence>
  </xs:complexType>

  <xs:simpleType name="TextType">
    <xs:restriction base="xs:string">
      <xs:maxLength value="255"/>
      <xs:minLength value="1"/>
    </xs:restriction>
  </xs:simpleType>

  <xs:simpleType name="ContentType">
    <xs:restriction base="xs:string">
      <xs:enumeration value="XML"/>
      <xs:enumeration value="EDIFACT"/>
      <xs:enumeration value="HL7"/>
      <xs:enumeration value="Binary"/>
      <xs:enumeration value="Other"/>
    </xs:restriction>
  </xs:simpleType>

  <xs:complexType name="ServiceTagType">
    <xs:simpleContent>
      <xs:extension base="vans:ServiceTagValueType">
        <xs:attribute name="name" use="required">
          <xs:simpleType>
            <xs:restriction base="xs:string">
              <xs:maxLength value="70"/>
              <xs:minLength value="1"/>
            </xs:restriction>
          </xs:simpleType>
        </xs:attribute>
      </xs:extension>
    </xs:simpleContent>
  </xs:complexType>

  <xs:simpleType name="ServiceTagValueType">
    <xs:restriction base="xs:string">
      <xs:maxLength value="70"/>
      <xs:minLength value="1"/>
    </xs:restriction>
  </xs:simpleType>

  <xs:complexType name="ReceiptType">
    <xs:choice>
      <xs:element name="NegativeVans" type="vans:NegativeVansType"/>
      <xs:element name="NegativeMessage" type="vans:NegativeMessageType"/>
      <xs:element name="PositiveMessage" type="vans:PositiveMessageType"/>
    </xs:choice>
  </xs:complexType>

  <xs:complexType name="NegativeVansType">
    <xs:sequence>
      <xs:element name="Error" type="vans:ErrorType" minOccurs="1"/>
      <xs:element name="OriginalEnvelopeIdentifier" type="vans:UUIDType"/>
      <xs:element name="OriginalMessage" type="vans:MetaInformationType" minOccurs="0"/>
    </xs:sequence>
  </xs:complexType>
  

  <xs:complexType name="PositiveMessageType">
    <xs:sequence>
      <xs:element name="OriginalEnvelopeIdentifier" type="vans:UUIDType"/>
      <xs:element name="OriginalMessage" type="vans:MetaInformationType"/>
    </xs:sequence>
  </xs:complexType>

  <xs:complexType name="NegativeMessageType">
    <xs:sequence>
      <xs:element name="Error" type="vans:ErrorType"/>
      <xs:element name="OriginalEnvelopeIdentifier" type="vans:UUIDType"/>
      <xs:element name="OriginalMessage" type="vans:MetaInformationType"/>
    </xs:sequence>
  </xs:complexType>
  

  <xs:complexType name="ErrorType">
    <xs:sequence>
      <xs:element name="Code" type="xs:positiveInteger" minOccurs="0"/>
      <xs:element name="Description">
        <xs:simpleType>
          <xs:restriction base="xs:string">
            <xs:minLength value="1"/>
            <xs:maxLength value="512"/>
          </xs:restriction>
        </xs:simpleType>
      </xs:element>
    </xs:sequence>
  </xs:complexType>
</xs:schema>
'''


def factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
def schema = factory.newSchema(new StreamSource(new StringReader(xsdString)))
def validator = schema.newValidator()
validator.validate(new StreamSource(new StringReader(xmlString)))


assert 5 == 5